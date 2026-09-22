<?php

declare(strict_types=1);

namespace App\Model\Entity;

use App\Model\Shared\VehicleTypeCategory;
use App\Repository\VehicleTypeRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: VehicleTypeRepository::class)]
#[ORM\Table(name: 'vehicle_type')]
class VehicleType
{
    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: 'IDENTITY')]
    #[ORM\Column(name: 'id', nullable: false)]
    private ?int $id = null;

    //    TODO: not blank
    #[Assert\NotNull]
    #[ORM\Column(name: 'name', nullable: false)]
    private ?string $name = null;

    //    TODO: not blank
    // TODO: Enum einführen und auch nur diese Werte in DB erlauben
    #[Assert\NotNull]
    #[ORM\Column(name: 'type', nullable: false, enumType: VehicleTypeCategory::class)]
    private ?VehicleTypeCategory $type = null;

    #[Assert\NotNull]
    #[ORM\ManyToOne(targetEntity: Person::class, cascade: ['persist'])]
    #[ORM\JoinColumn(name: 'person_responsible', referencedColumnName: 'id', nullable: false)]
    private ?Person $personResponsible = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function setId(?int $id): static
    {
        $this->id = $id;

        return $this;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(?string $name): static
    {
        $this->name = $name;

        return $this;
    }

    public function getType(): ?VehicleTypeCategory
    {
        return $this->type;
    }

    public function setType(?VehicleTypeCategory $type): static
    {
        $this->type = $type;

        return $this;
    }

    public function getPersonResponsible(): ?Person
    {
        return $this->personResponsible;
    }

    public function setPersonResponsible(?Person $personResponsible): static
    {
        $this->personResponsible = $personResponsible;

        return $this;
    }
}
